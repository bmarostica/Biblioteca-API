<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BiBlioteca</title>
    <style>
        * {
            margin: 0 0 10px;
            padding: 0;
            border: 0 none;
        }

        html {
            font-size: 62.5%;
        }

        img {
            max-width: 100%;
            height: auto;
        }

        @media (max-width: 600px) {
            table {
                width: 100% !important;
                min-width: 320px;
            }
            h1 {
                font-size: 1.rem;
            }
        }
    </style>
</head>
<body>
<div style="background-position: center; opacity: 0.8; background-repeat: no-repeat; background-image: url('https://st2.depositphotos.com/27316046/47536/v/600/depositphotos_475365840-stock-illustration-yellow-books-flying-banner-template.jpg');">
    <table style="border-radius: 20%; width: 600px; height: 350px; margin: 20px auto; font-family: sans-serif; opacity: 1; ">
        <tr>
            <td style="width: 100px;"></td>
            <td>
                <div style="padding: 20px; margin-top: 50px;">
                    <h1 style="color: #fffff">Olá ${nome}</h1>

                    <h2><p><bold>${mensagem}</bold></h2></p>
                </div>
            </td>
            <td style="width: 10px;"></td>
        </tr>
    </table>
</div>
</body>
</html>