<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>

    <asset:link rel="import" href="polymer/polymer.html" />
    <asset:javascript src="webcomponentsjs/webcomponents.min.js" />

    <asset:javascript src="d3/d3.min.js" />
    <asset:javascript src="trianglify/trianglify.min.js" />

    <asset:stylesheet href="animate.css/animate.min.css" />
    <asset:stylesheet src="application.css" />

    <asset:link rel="import"  href="start-page-middle.html"/>

    <style>
        .google-btn {
            float: right;
            position: relative;
            z-index: 1;
            margin: 24px;
            width: 246px;
            height: 54px;
            display: block;
            background: url("/assets/google_sign_in.png") no-repeat 0 0;
        }
        .google-btn :hover {
            background-position: 0px -64px;
        }
        .google-btn :active {
            background-position: 0px -128px;
        }
    </style>

</head>

<body fullbleed="" class="no-scrollbars" >

    %{--<sec:ifLoggedIn>--}%
    %{--<oauth:connected provider="google" />--}%
    %{--<s2o:ifLoggedInWith provider="google"></s2o:ifLoggedInWith>--}%
    %{--<s2o:ifNotLoggedInWith provider="google"></s2o:ifNotLoggedInWith>--}%

    <sec:ifNotLoggedIn>
        <oauth:connect provider="google" id="google-connect-link" class="google-btn animated bounceInDown delayed2_25" />
    </sec:ifNotLoggedIn>

    <start-page-middle id="middle" />

</body>
</html>
