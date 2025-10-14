using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Textbook_Problem_14_1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void displayButton_Click(object sender, EventArgs e)
        {
            try
            {
                List<int> numbers = ReadFile("random.txt");

                if (numbers == null || numbers.Count == 0)
                {
                    MessageBox.Show("No numbers were read from the file.");
                    return;
                }

                numbers.RemoveAll(n => n < 0);

                List<int> filtered = numbers.FindAll(n => n >= 1 && n <= 10);

                findAllListBox.Items.Clear();
                foreach (int number in filtered)
                {
                    findAllListBox.Items.Add(number);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("An error occurred: " + ex.Message);

            }
        }

        private List<int> ReadFile(string filename)
        {
            List<int> numbers = new List<int>();

            string filePath = Path.Combine(Directory.GetParent(Application.StartupPath).Parent.FullName, "random.txt");
            MessageBox.Show($"finding path: {filePath}");

            if (!File.Exists(filePath))
            {
                MessageBox.Show("The file 'random.txt' was not found in the application directory.");
                return numbers;
            }

            string[] lines = File.ReadAllLines(filePath);
            foreach (string line in lines)
            {
                if (int.TryParse(line, out int number))
                {
                    numbers.Add(number);
                }
            }
            return numbers;
        }
    }
}
