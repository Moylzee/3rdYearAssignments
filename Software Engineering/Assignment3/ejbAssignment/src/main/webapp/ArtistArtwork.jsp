<%@ include file = "/jspf/header.jspf" %>

<c:set var='view' value='/artistArtwork' scope='session' />

<h2><fmt:message key='Artby' /> ${selectedArtist.firstname} ${selectedArtist.surname} </h2>

    <c:forEach var="artwork" items="${artistArtwork}" varStatus="iter">
        <img src="${initParam.artworkImagePath}${artwork.coverimage}.jpg"
             alt="No cover image found" width="250" height="300">
        <br><br>
        <h1><fmt:message key="${artwork.title}"/></h1>
        <h2><fmt:message key='pubyear' /> ${artwork.year}</h2>
    </c:forEach>

<%@ include file = "/jspf/footer.jspf" %>