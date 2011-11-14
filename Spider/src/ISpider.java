import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: zalim
 * Date: 11/14/11
 * Time: 2:12 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ISpider {
    void Run(IUrlIterator urlIterator, IDataProcessor processor) throws IOException;
}
