package weird.io;

/**
 * Http Request interface is a contract for all http related calls.
 *
 * @author Varun Gaur
 */
public interface HttpRequest {

    /**
     * Sends the http request.
     * @param endpoint the request url
     * @return the stream of data as stream builder
     */
    StringBuilder sendRequest(String endpoint);
}
