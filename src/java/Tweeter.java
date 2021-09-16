import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;

import java.io.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Tweeter
{
    private Twitter twitter;
    private PrintStream p;
    private List<Status> statuses;
    private Utils utils = new Utils();


    public Tweeter(PrintStream pr)
    {
        twitter = TwitterFactory.getSingleton();
        p = pr;
        statuses = new ArrayList<Status>();
    }
    public void tweetOut(String message) throws TwitterException, IOException
    {
        Status status = twitter.updateStatus(message);
        System.out.println("Status update successful to "+ status.getText() +"\n");
    }
    public void queryHandle() throws TwitterException, IOException
    {
        statuses.clear();
        fetchTweets();
        int counter = statuses.size();
        while(counter > 0)
        {
            counter--;
            System.out.println("Tweet "+counter+"="+statuses.get(counter).getText());
        }
    }


    private void fetchTweets() throws TwitterException, IOException
    {
        String pgno = utils.getProperties().getProperty("pagenumber");
        int pno = Integer.parseInt(pgno);
        String count = utils.getProperties().getProperty("maxcount");
        int csize = Integer.parseInt(count);
        Paging page = new Paging(pno,csize);
        statuses.addAll(twitter.getHomeTimeline(page));
    }

    public void saQuery(String searchTerm) throws IOException
    {
        Query query = new Query(searchTerm);
        String size = utils.getProperties().getProperty("searchResultSize");
        int ressize = Integer.parseInt(size);
        String since = utils.getProperties().getProperty("searchResultSince");
        query.setCount(ressize);
        query.setSince(since);
        try
        {
            QueryResult result = twitter.search(query);
            System.out.println(result.getTweets().size() + " results obtained");
            for(Status tweet : result.getTweets())
            {
                System.out.println("@"+tweet.getUser().getName()+" tweeted "+tweet.getText());
            }
        }
        catch (TwitterException e)
        {
            e.printStackTrace();
        }
        System.out.println();
    }
}