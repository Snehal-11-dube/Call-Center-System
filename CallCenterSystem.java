package ds_practical;
import java.util.Scanner;

public class CallCenterSystem
{
    int maxSize;
    String customerIDQueue[];
    int callTimeQueue[];
    int front;
    int rear;
    int count;
    Scanner sc;

    CallCenterSystem()
    {
        maxSize = 100;
        customerIDQueue = new String[maxSize];
        callTimeQueue = new int[maxSize];
        front = 0;
        rear = -1;
        count = 0;
        sc = new Scanner(System.in);
    }

    public void addCall(String customerID, int callTime)
    {
        if(count == maxSize)
        {
            System.out.println("Call Queue is full");
        }
        else
        {
            rear = (rear + 1) % maxSize;
            customerIDQueue[rear] = customerID;
            callTimeQueue[rear] = callTime;
            count++;
            System.out.println("Call from customer " + customerID + " (" + callTime + " minutes) added to the queue.");
        }
    }

    public void answerCall()
    {
        if(count == 0)
        {
            System.out.println("No calls to answer.");
        }
        else
        {
            String customerID = customerIDQueue[front];
            int callTime = callTimeQueue[front];
            System.out.println("Answered call from customer " + customerID + " (" + callTime + " minutes)");
            front = (front + 1) % maxSize;
            count--;
        }
    }

    public void viewQueue()
    {
        if(count == 0)
        {
            System.out.println("No calls in the queue.");
        }
        else
        {
            System.out.println("Current call queue:");
            int current = front;
            for(int i = 0; i < count; i++)
            {
                System.out.println((i + 1) + ". Customer ID: " + customerIDQueue[current] +
                        " | Call Time: " + callTimeQueue[current] + " minutes");
                current = (current + 1) % maxSize;
            }
        }
    }

    public boolean isQueueEmpty()
    {
        if(count == 0)
        {
            System.out.println("Call queue is empty.");
            return true;
        }
        else
        {
            System.out.println("Call queue is not empty. Total calls: " + count);
            return false;
        }
    }

    public void menu()
    {
        while(true)
        {
            System.out.println("\n--- CALL CENTER MENU ---");
            System.out.println("1. Add Call");
            System.out.println("2. Answer Call");
            System.out.println("3. View Queue");
            System.out.println("4. Check if Queue is Empty");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch(choice)
            {
                case "1":
                    System.out.print("Enter Customer ID: ");
                    String customerID = sc.nextLine();
                    System.out.print("Enter Call Time (minutes): ");
                    int callTime = Integer.parseInt(sc.nextLine());
                    addCall(customerID, callTime);
                    break;

                case "2":
                    answerCall();
                    break;

                case "3":
                    viewQueue();
                    break;

                case "4":
                    isQueueEmpty();
                    break;

                case "5":
                    System.out.println("Exiting Call Center System");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice. Enter number from 1 to 5.");
            }
        }
    }

    public static void main(String s[])
    {
        CallCenterSystem c1 = new CallCenterSystem();
        c1.menu();
    }
}
