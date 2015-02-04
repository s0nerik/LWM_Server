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

    <script src="https://apis.google.com/js/client:platform.js" async defer></script>

</head>

<body fullbleed="" class="no-scrollbars" >

    <span id="signinButton" style="float: right; padding: 24px;" class="animated bounceInDown delayed2_25">
        <span
                class="g-signin"
                data-callback="signinCallback"
                data-clientid="CLIENT_ID"
                data-cookiepolicy="single_host_origin"
                data-requestvisibleactions="http://schema.org/AddAction"
                data-scope="https://www.googleapis.com/auth/plus.login">
        </span>
    </span>

    <div>
        <sec:ifLoggedIn>
            Welcome <sec:username />!
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <oauth:connect provider="google" id="google-connect-link">Google</oauth:connect>
        </sec:ifNotLoggedIn>

        Logged with google?
        <s2o:ifLoggedInWith provider="google">yes</s2o:ifLoggedInWith>
        <s2o:ifNotLoggedInWith provider="google">no</s2o:ifNotLoggedInWith>
    </div>

    <start-page-middle />

</body>
</html>
