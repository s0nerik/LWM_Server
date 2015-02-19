<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <meta name="layout" content="main">
</head>

<body>

    <ul>
    <g:each in="${users}" var="user">
        <g:each in="${user}">
            <li>${it}</li>
        </g:each>
    </g:each>
    </ul>

</body>
</html>
