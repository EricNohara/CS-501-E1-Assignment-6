namespace Textbook_Problem_14_1
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.findAllListBox = new System.Windows.Forms.ListBox();
            this.displayButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // findAllListBox
            // 
            this.findAllListBox.FormattingEnabled = true;
            this.findAllListBox.ItemHeight = 25;
            this.findAllListBox.Location = new System.Drawing.Point(225, 75);
            this.findAllListBox.Name = "findAllListBox";
            this.findAllListBox.Size = new System.Drawing.Size(162, 254);
            this.findAllListBox.TabIndex = 1;
            // 
            // displayButton
            // 
            this.displayButton.Location = new System.Drawing.Point(248, 348);
            this.displayButton.Name = "displayButton";
            this.displayButton.Size = new System.Drawing.Size(109, 64);
            this.displayButton.TabIndex = 2;
            this.displayButton.Text = "Display";
            this.displayButton.UseVisualStyleBackColor = true;
            this.displayButton.Click += new System.EventHandler(this.displayButton_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.displayButton);
            this.Controls.Add(this.findAllListBox);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.ListBox findAllListBox;
        private System.Windows.Forms.Button displayButton;
    }
}

