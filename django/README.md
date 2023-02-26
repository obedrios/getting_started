It is highly recommended to structure your code and  dependencies into virtual environments or containers. It is recommend checking  out Python development with `virtualenv` or `Docker`

## Configure virtual environments in python

0. Install python `pip` and `setup-tools` :

   1. Ubuntu/Debian:

      ````bash
      sudo apt install python3-pip python3-setuptools
      ````

1. Install the following python packages 

   ````bash
   pip3 install virtualenv virtualenvwrapper
   ````

2. Add the following line in `.bashrc`

````bash
# Configure Virtual Environments for Python
export WORKON_HOME=$HOME/.virtualenvs
export VIRTUALENVWRAPPER_PYTHON=/usr/bin/python3
source /usr/local/bin/virtualenvwrapper.sh
````

​		If you are using MacOS you should modify your user `.zshrc` file, for example if you are using Python 3.9  append the following lines:

````bash
export WORKON_HOME=$HOME/.virtualenvs
export VIRTUALENVWRAPPER_PYTHON="/Library/Frameworks/Python.framework/Versions/3.9/bin/python3"
source /Library/Frameworks/Python.framework/Versions/3.9/bin/virtualenvwrapper.sh
````

in general, you should adapt the shown example to your current python version and installation.

3. The following commands are used to create, query, enter and exit from a python virtual environment

   ````bash
   lsvirtualenv # Query for existing virtual envs
   mkvirtualenv <venvname> # Create new virtual env
   ````

   ````bash
   workon <venvname> # To work with an existing venv
   deactivate  # To exit from an currently working venv
   ````
