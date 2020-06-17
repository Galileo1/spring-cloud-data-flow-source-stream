package weird.io;

import java.util.List;

import org.reactivestreams.Publisher;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import org.springframework.messaging.Message;

/**
 * Stream Emitter generic interface.
 * @param <T> class type
 *
 * @author Varun Gaur
 */
interface SourceEmitter<T> {

    @StreamEmitter
    @Output(Source.OUTPUT)
    Publisher<Message<List<T>>> publisher();
}
