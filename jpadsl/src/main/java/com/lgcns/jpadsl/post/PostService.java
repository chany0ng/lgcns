package com.lgcns.jpadsl.post;

import com.querydsl.core.BooleanBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private static final int countPerPage = 10;
    private final PostRepository repository;
    private final ReplyRepository replyRepository;
    private final HashtagRepository hashtagRepository;

    private final PostMapper mapper;
    private final ReplyMapper replyMapper;
    private final HashtagMapper hashtagMapper;

    public List<PostDTO> getPosts(Integer pageNo) {
        Sort sort = Sort.by("id").descending();
        Pageable paging = PageRequest.of(pageNo, countPerPage, sort);
//        List<Post> posts = repository.findAll();
        Page<Post> posts = repository.findAll(paging);
        long totalElements = posts.getTotalElements();
        System.out.println("totalElements = " + totalElements);
        List<PostDTO> dtos = new ArrayList<>();
        for (Post post : posts) {
            dtos.add(entityToDTO(post));
        }
        return dtos;
    }

    public List<PostDTO> search(String title, String body) {
        BooleanBuilder bb = new BooleanBuilder();
        if (StringUtils.hasText(title)) {
            bb.and(QPost.post.title.contains(title));
        }
        if (StringUtils.hasText(body)) {
            bb.and(QPost.post.body.body.contains(body));
        }

        Iterable<Post> posts = repository.findAll(bb);
        List<PostDTO> dtos = new ArrayList<>();
        for (Post post : posts) {
            dtos.add(entityToDTO(post));
        }
        return dtos;
    }

    public PostDTO createPost(PostSaveDTO dto) {
        Post post = repository.save(dtoToEntity(dto));
        List<Hashtag> hashtags = dto.getHashtags().stream().map(h -> {
            Hashtag hashtag = hashtagRepository.findByTag(h.getTag())
                    .orElseGet(() -> hashtagRepository.save(new Hashtag(h.getTag())));
            hashtag.addPost(post);
            return hashtag;
        }).toList();

        return entityToDTO(post, null, hashtags);
    }

    public PostDTO getPost(Long postid) {
        List<Reply> replies = replyRepository.findAllByPostId(postid);

        return entityToDTO(repository.findById(postid).orElseThrow(), replies);
    }

    public PostDTO editPost(PostSaveDTO dto) {
//        return entityToDTO(repository.save(dtoToEntity(dto)));
        Post post = repository.findById(dto.getId()).orElseThrow();
        post.setTitle(dto.getTitle());
        post.setWriter(dto.getWriter());
        post.setBody(mapper.toEntity(dto.getBody()));

        return mapper.toDTO(repository.save(post));
    }

    public void removePost(Long postid) {
        repository.deleteById(postid);
    }

    public int removeById(Long postid) {
        return repository.removeById(postid);
    }

    private PostDTO entityToDTO(Post post) {
        return entityToDTO(post, null);
    }

    private PostDTO entityToDTO(Post post, List<Reply> replies) {
        return entityToDTO(post, replies, null);
    }

    private PostDTO entityToDTO(Post post, List<Reply> replies, List<Hashtag> hashtags) {
        return new PostDTO(post.getId(), post.getTitle(), post.getWriter(), mapper.toDTO(post.getBody()), replyMapper.toDTOList(replies), hashtagMapper.toDTOList(hashtags));
    }

    private Post dtoToEntity(PostSaveDTO dto) {
        Post post = Post.builder()
                .title(dto.getTitle())
                .writer(dto.getWriter())
//                .body(mapper.toEntity(dto.getBody()))
                .build();
        PostBody body = mapper.toEntity(dto.getBody());
        post.setBody(body);
        return post;
    }

    public List<ReplyDTO> getReplies(Long postid) {
        List<Reply> allByPostId = replyRepository.findAllByPostId(postid);
        return replyMapper.toDTOList(allByPostId);
    }

    public ReplyDTO createReply(Long postid, @Valid ReplySaveDTO dto) {
        Post post = repository.findById(postid).orElseThrow();
        Reply reply = replyMapper.toEntity(dto);
        reply.setPost(post);
        return replyMapper.toDTO(replyRepository.save(reply));
    }

    public ReplyDTO editReply(@Valid ReplySaveDTO dto) {
        Reply reply = replyRepository.findById(dto.getId()).orElseThrow();
        reply.setReply(dto.getReply());
        reply.setReplier(dto.getReplier());
        return replyMapper.toDTO(replyRepository.save(reply));
    }

    public void removeReply(Long replyid) {
        Reply reply = replyRepository.findById(replyid).orElseThrow();
        replyRepository.delete(reply);
    }
}
