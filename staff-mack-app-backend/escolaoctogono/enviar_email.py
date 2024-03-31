import sys
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import base64
import traceback

def enviar_email(dest_email, subject, body, image_path):
    # Configurações do servidor SMTP do Outlook
    outlook_server = 'smtp-mail.outlook.com'
    port = 587  # Porta padrão para TLS

    email = 'escolaoctogono@hotmail.com'
    password = 'D6c5s9t5h9f4.'

    try:
        # Criando a mensagem
        msg = MIMEMultipart()
        msg['From'] = email
        msg['To'] = dest_email
        msg['Subject'] = subject

        # Lendo a imagem e convertendo para base64
        if image_path:
            with open(image_path, 'rb') as f:
                image_data = f.read()
                image_base64 = base64.b64encode(image_data).decode('utf-8')

            # Corpo da mensagem (HTML)
            body_with_image = f'<html><body><p>{body}</p><img src="data:image/png;base64,{image_base64}"></body></html>'
            msg.attach(MIMEText(body_with_image, 'html'))
        else:
            msg.attach(MIMEText(body, 'plain'))

        # Iniciando a conexão SMTP
        server = smtplib.SMTP(outlook_server, port)
        server.starttls()

        # Fazendo login
        server.login(email, password)

        # Enviando o e-mail
        text = msg.as_string()
        server.sendmail(email, dest_email, text)

        # Encerrando a conexão
        server.quit()

        print('E-mail enviado com sucesso!')

    except Exception as e:
        print('Erro ao enviar e-mail:')
        print(traceback.format_exc())

if __name__ == "__main__":
    if len(sys.argv) < 4:
        print("Uso: python enviar_email.py <dest_email> <subject> <body> [image_path]")
        sys.exit(1)

    dest_email = sys.argv[1]
    subject = sys.argv[2]
    body = sys.argv[3]
    image_path = sys.argv[4] if len(sys.argv) > 4 else None

    enviar_email(dest_email, subject, body, image_path)
