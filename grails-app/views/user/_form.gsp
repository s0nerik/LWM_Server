<%@ page import="lwm_server.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${userInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="user.name.label" default="Name" />
		
	</label>
	<g:textField name="name" maxlength="50" value="${userInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'avatar_url', 'error')} ">
	<label for="avatar_url">
		<g:message code="user.avatar_url.label" default="Avatarurl" />
		
	</label>
	<g:textField name="avatar_url" value="${userInstance?.avatar_url}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'OAuthIDs', 'error')} ">
	<label for="OAuthIDs">
		<g:message code="user.OAuthIDs.label" default="OA uth ID s" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.OAuthIDs?}" var="O">
    <li><g:link controller="OAuthID" action="show" id="${O.id}">${O?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="OAuthID" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'OAuthID.label', default: 'OAuthID')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="user.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${userInstance?.accountExpired}" />

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="user.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${userInstance?.accountLocked}" />

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="user.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${userInstance?.enabled}" />

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="user.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${userInstance?.passwordExpired}" />

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'playbackHistory', 'error')} ">
	<label for="playbackHistory">
		<g:message code="user.playbackHistory.label" default="Playback History" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.playbackHistory?}" var="p">
    <li><g:link controller="playbackHistoryItem" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="playbackHistoryItem" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'playbackHistoryItem.label', default: 'PlaybackHistoryItem')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'songs', 'error')} ">
	<label for="songs">
		<g:message code="user.songs.label" default="Songs" />
		
	</label>
	<g:select name="songs" from="${lwm_server.Song.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.songs*.id}" class="many-to-many"/>

</div>

