# Required Libraries

import sys
from os.path import dirname, join
from com.chaquo.python import Python

# Method That We Will Use

def main(Code):

    # First We Will Execute The Code Then We Will Save It In txt File
    # Then We Will Return The Output

    # Directory Path For txt File
    file_dir = str(Python.getPlatform().getApplication().getFilesDir())

    # File Name
    filename = join(dirname(file_dir), 'file.txt')

    # Executing Our Code Using stdout Concept
    try:
        # Reference To The Original Standard Output
        original_stdout = sys.stdout
        
        # Open New File (file.txt) With Intention To Write Data And Change Standard Output To Our File
        sys.stdout = open(filename, 'w', encoding = 'utf8', errors="ignore")

        # Execute The Code
        exec(Code) # it will execute our code and save output in file
        
        # Closing The File After We Write The Data
        sys.stdout.close()

        # Reset The Standard Output To Its Original Value
        sys.stdout = original_stdout

        # Saving The Output From The File to The Variable
        output = open(filename, 'r').read()

    except Exception as e:
        
        # Handling Errors
        # We Will Save The Error Args In The Variable
        
        sys.stdout = original_stdout

        # Saving The Error In The Output Variable

        output = e

    # Return The Output
    return str(output)
