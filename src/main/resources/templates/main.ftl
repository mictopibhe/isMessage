<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>
        <@l.logout />
    </div>
    <div>
        <form method="POST">
            <input type="text" name="text" id="text" placeholder="Enter message" value="${message.text!''}">
            <input type="text" name="tag" id="tag" placeholder="Enter tag" value="${message.tag!''}">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Enter">
                    <#if errors?has_content>
                        <#if errors.text?has_content>
                            <div style="color:red">${errors.text}</div>
                        </#if>
                        <#if errors.tag?has_content>
                            <div style="color:red">${errors.tag}</div>
                        </#if>
                    </#if>
        </form>
    </div>
    <div>Messages list:</div>
    <form method="get" action="/">
        <input type="text" name="filter" value="${filter}">
        <button type="submit">Search</button>
    </form>

    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.author.username}</strong>
        </div>
    <#else>
        No message
    </#list>

</@c.page>