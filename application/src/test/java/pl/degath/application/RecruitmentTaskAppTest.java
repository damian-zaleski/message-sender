package pl.degath.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Tag("requires-cassandra")
class RecruitmentTaskAppTest {
    @Test
    @DisplayName("Truly excessive test just to bump test coverage ^_^")
    void contextLoads() {
        RecruitmentTaskApp.main(new String[]{});
    }
}
