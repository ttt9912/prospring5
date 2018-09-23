package ch11.p2_async_tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/*
 * @Async: execute a task asynchronously
 *
 * AsyncResult: implementation of Future.
 *              can be used to retrieve the result of the execution later
 */
@Service("asyncService")
class AsyncService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncService.class);

    @Async
    public void asyncTask() {
        logger.info("Start execution of async task");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Completed execution of async task");
    }

    @Async
    public Future<String> asyncWithResult(final String name) {
        logger.info("Start execution of async task with return for {}", name);

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Completed execution of async task with return for {}", name);

        return new AsyncResult<>("Hello " + name);
    }
}
