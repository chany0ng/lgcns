package com.lgcns.pipeline.batch;

import com.lgcns.pipeline.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.batch.infrastructure.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.stream.Stream;

@Configuration
@RequiredArgsConstructor
public class ChunkBatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final UserRepository userRepository;

    @Bean
    public Job chunkJob() {
        return new JobBuilder("chunkJob", jobRepository)
                .start(chunkStep())
                .build();
    }

    @Bean
    public Step chunkStep() {
        return new StepBuilder("chunkStep", jobRepository)
                .<String, String>chunk(2)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    //    @Bean
//    public ItemReader<String> reader() {
//        return new ListItemReader<>(List.of("hong", "kim", "lee"));
//    }
    @Bean
    public RepositoryItemReader<String> reader() {
        return new RepositoryItemReaderBuilder<String>()
                .name("reader")
                .repository(userRepository)
                .methodName("findNames")
                .pageSize(2)
                .sorts(Map.of("id", Sort.Direction.ASC))
                .build();
    }

    @Bean
    public ItemProcessor<String, String> processor() {
        return StringUtils::capitalize;
    }

    @Bean
    public ItemWriter<String> writer() {
        return items -> Stream.of(items).forEach(System.out::println);
    }
}
