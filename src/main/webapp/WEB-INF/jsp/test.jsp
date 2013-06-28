<%@ include file="header.jsp"%>
<div class="container">

      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit">
        <h1>Bigdata visualization</h1>
        <p>This is a Pink elephant TV statistics viewer, feel free to send us your <a href="/contact/">recommendations </a>!</p>
        <p><a href="#" class="btn btn-primary btn-large">Learn more »</a></p>
      </div>

      <!-- Example row of columns -->
      <ul>
      	<c:forEach var="real" items="${rts}">
      		Key: ${rts.metricKey}<br/>
      		Minute: ${rts.minute}<br/>
      		Quantity: ${rts.quantity}<br/>
      	</c:forEach>
      </ul>

      <hr>
    </div>
<%@ include file="footer.jsp"%>