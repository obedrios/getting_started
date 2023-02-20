#!/usr/bin/env python3

import socket


if __name__ == '__main__':
    user_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    user_socket.connect(('127.0.0.1', 9000))
    webcmd = 'GET https://127.0.0.1/ HTTP/1.0\r\n\r\n'.encode()
    user_socket.send(webcmd)
    #
    while True:
        webdata = user_socket.recv(512)
        if len(webdata) < 1:
            break
        print(webdata.decode(), end='')
