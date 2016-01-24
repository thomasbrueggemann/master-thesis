# Master-Thesis

Automatic privacy risk assessment for mobile health apps

## Software

### Setup

Create file in path ```/decompiler/libs/googleplay-api/config.py``` with the following content 
```python
# separator used by search.py, categories.py, ...
SEPARATOR = ";"

LANG            = "de_DE" # can be en_US, fr_FR, ...
ANDROID_ID      = "" # "xxxxxxxxxxxxxxxx"
GOOGLE_LOGIN    = "" # "username@gmail.com"
GOOGLE_PASSWORD = ""
AUTH_TOKEN      = None # "yyyyyyyyy"

# force the user to edit this file
if any([each == None for each in [ANDROID_ID, GOOGLE_LOGIN, GOOGLE_PASSWORD]]):
    raise Exception("config.py not updated")
```

The ANDROID_ID is the unique ID of a real Android hardware device. This is used to download the apps from the Google PlayStore.