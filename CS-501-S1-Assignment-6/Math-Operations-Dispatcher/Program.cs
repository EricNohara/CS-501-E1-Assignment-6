class Program
{
    // declare the delegate
    public delegate int MathOperation(int nOne, int nTwo);

    // math operation methods
    public int Add(int nOne, int nTwo) => nOne + nTwo;
    public int Subtract(int nOne, int nTwo) => nOne - nTwo;
    public int Multiply(int nOne, int nTwo) => nOne * nTwo;
    public int Divide(int nOne, int nTwo) => nOne / nTwo;

    public int PerformOperation(int nOne, int nTwo, MathOperation op)
    {
        // invoke the delegate
        return op(nOne, nTwo);
    }

    public static int GetIntFromInput(string msg)
    {
        int n;
        while (true)
        {
            Console.Write(msg);
            string? input = Console.ReadLine();

            // Try to parse input
            if (int.TryParse(input, out n))
                break; // valid input — exit inner loop
            else
                Console.WriteLine("Invalid input. Please enter a valid integer.");
        }

        return n;
    }

    public static char GetCharFromInput(string msg, char[] cs)
    {
        while (true)
        {
            Console.Write($"{msg} ({string.Join(", ", cs)}): ");
            string? input = Console.ReadLine();

            // Ensure the input is not null or empty and is a single valid operator
            if (!string.IsNullOrEmpty(input))
            {
                char o = input[0];
                if (cs.Contains(o))
                    return o;
            }

            Console.WriteLine("Invalid input. Please enter one of (+, -, *, /).");
        }
    }

    // main method
    public static void Main(string[] args)
    {
        Program p = new Program();

        char[] validOps = { '+', '-', '*', '/' };
        char[] validResponses = { 'y', 'n' };
        bool isUserDone = false;

        while (!isUserDone)
        {
            // get input
            int nOne = GetIntFromInput("Enter first number: ");
            int nTwo = GetIntFromInput("Enter second number: ");
            char opr = GetCharFromInput("Choose operation", validOps);

            // instantiate delegate based on input
            MathOperation operation;
            switch (opr)
            {
                case '+':
                    operation = new MathOperation(p.Add);
                    break;
                case '-':
                    operation = new MathOperation(p.Subtract);
                    break;
                case '*':
                    operation = new MathOperation(p.Multiply);
                    break;
                case '/':
                    if (nTwo == 0)
                    {
                        Console.WriteLine("Error: Division by zero.");
                        continue;
                    }
                    operation = new MathOperation(p.Divide);
                    break;
                default:
                    // will never get here
                    continue;
            }

            // perform operation and print result
            int result = p.PerformOperation(nOne, nTwo, operation);
            Console.WriteLine($"Result: {nOne} {opr} {nTwo} = {result}");

            // ask the user if they are done
            char isDone = GetCharFromInput("Are you done?", validResponses);
            isUserDone = isDone == 'y';
        }
    }
}