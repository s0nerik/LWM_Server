<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <meta name="layout" content="main">

    <asset:link rel="import" href="core-scaffold/core-scaffold.html" />

</head>

<body>

    <core-scaffold>

    </core-scaffold>

    <ul>
    <g:each in="${users}">
        <li>${it}</li>
    </g:each>
    </ul>

</body>
</html>
