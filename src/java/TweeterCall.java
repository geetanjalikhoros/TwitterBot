import twitter4j.TwitterException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class TweeterCall
{
    private static PrintStream p;
    public static void main (String args[]) throws TwitterException, IOException
    {
        Tweeter obj = new Tweeter(p);
        while(true)
        {
            System.out.println("Choose your functionality: \n 1.Post Tweet \n 2.Retrieve Tweets \n 3.Search Tweets \n 4.Finish");
            Scanner sc = new Scanner(System.in).useDelimiter("\n");
            int choice = sc.nextInt();
            if (choice == 1)
            {
                System.out.println("Enter message to post: ");
                String message = sc.next();
                if (message.length() < 1)
                    System.out.println("Please enter atleast 1 character\n");
                if (message.length() >= 1 && message.length() <= 280)
                    obj.tweetOut(message);
                else
                    System.out.println("Maximum limit reached: Only 280 characters allowed\n");
            }
            else if (choice == 2)
            {
                obj.queryHandle();
            }
            else if (choice == 3)
            {
                System.out.println("Enter your search term:");
                String searchTerm = sc.next();
                if (searchTerm.length() < 1)
                    System.out.println("Please enter atleast 1 character\n");
                else
                    obj.saQuery(searchTerm);
            }
            else
            {
                System.exit(0);
            }
        }
    }
}
