package com.example.goodmorning.function;

import java.util.Optional;
import java.util.function.Function;

import com.example.common.exception.SystemException;
import com.example.common.model.CommonOutput;
import com.example.common.model.ErrorOutput;
import com.example.goodmorning.model.GoodMorningInput;
import com.example.goodmorning.model.GoodMorningOutput;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class GoodMorningFunction
        implements Function<Message<GoodMorningInput>, Message<CommonOutput<GoodMorningOutput>>> {

    @Override
    public Message<CommonOutput<GoodMorningOutput>> apply(Message<GoodMorningInput> input) {

        log.info(this.getClass().getName() + " has started!");
        log.info("[input]" + input.toString());

        final CommonOutput<GoodMorningOutput> output = new CommonOutput<>();

        try {

            GoodMorningInput goodMorningInput = input.getPayload();

            // 入力検証
            Optional<ErrorOutput> error = validate(goodMorningInput);
            if (error.isPresent()) {
                output.setError(error.get());
                return MessageBuilder.withPayload(output).build();
            }

            GoodMorningOutput goodMorningOutput = new GoodMorningOutput("こんばんわ");
            output.setResult(goodMorningOutput);
            log.info("[Output]" + goodMorningOutput.toString());
            return MessageBuilder.withPayload(output).build();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SystemException("", e.getStackTrace(), e);
        }
    }

    Optional<ErrorOutput> validate(GoodMorningInput input) {
        // try {
        // validationService.execute(input);
        // } catch (BusinessException e) {
        // return Optional.of(new ErrorOutput(e.getCode(), e.getMessage()));
        // }
        return Optional.empty();
    }
}
