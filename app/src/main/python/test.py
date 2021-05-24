import requests


def open():
    res = requests.get('http://192.168.4.1/led?params=1')
    return res.text

def close():
    res = requests.get('http://192.168.4.1/led?params=0')
    return res.text
