#!/usr/bin/env python3

from socket import *

def create_server(host_address, port):
    server_socket = socket(AF_INET, SOCK_STREAM)
    try:
        server_socket.bind((host_address, port))
        server_socket.listen(5)
        #
        while True:
            (client_socket, address) = server_socket.accept()
            read = client_socket.recv(5000).decode()
            pieces = read.split("\n")
            if len(pieces) > 0:
                print(pieces[0])
            data = "HTTP/1.1 200 OK\r\n"
            data += "Content-Type: text/html; charset=utf-8\r\n"
            data += "\r\n"
            data += "<html><body>Hello World</body></html>\r\n\r\n"
            client_socket.sendall(data.encode())
            client_socket.shutdown(SHUT_WR)
    except KeyboardInterrupt:
        print("\nShutting down...\n")
    except Exception as ex:
        print("Error:\n")
        print(ex)
    finally:
        server_socket.close()


if __name__ == '__main__':
    print("Access http://localhost:9000")
    create_server('localhost',9000)