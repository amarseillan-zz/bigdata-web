<%@ include file="header.jsp"%>
<div class="container">

      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit">
        <h1>Bigdata visualization</h1>
        <p>This is a Pink elephant TV statistics viewer, feel free to send us your <a href="contact/">recommendations </a>!</p>
        <p><a href="#" class="btn btn-primary btn-large">Learn more »</a></p>
      </div>

      <!-- Example row of columns -->
      <div class="row">
        <div class="span4">
          <h2>Batch metrics</h2>
          <p>These are metrics generated using a loadfull of data, pig and hive.</p>
          <!-- <p><a class="btn btn-success" href="#container" id="avgdurationchannel">Average Duration By Channel</a></p>
          <p><a class="btn btn-success" href="#container" id="avgdurationcategory">Average Duration By Category</a></p>
          <p><a class="btn btn-success" href="#container" id="top10channels">Top 10 Channels</a></p>
          <p><a class="btn btn-success" href="#container" id="top10categories">Top 10 Categories</a></p>
          <p><a class="btn btn-success" href="#container" id="topchannelads">Channels With Most Ads</a></p> -->
          <div class="btn-group">
                <button class="btn btn-success dropdown-toggle" data-toggle="dropdown">Action <span class="caret"></span></button>
                <ul class="dropdown-menu">
                  <li><a href="#container" id="avgdurationchannel">Average Duration By Channel</a></li>
                  <li><a href="#container" id="avgdurationcategory">Average Duration By Category</a></li>
                  <li><a href="#container" id="top10channels">Top 10 Channels</a></li>
                  <li><a href="#container" id="top10categories">Top 10 Categories</a></li>
                  <li><a href="#container" id="topchannelads">Channels With Most Ads</a></li>
                </ul>
              </div>
        </div>
        <div class="span4">
          <h2>Real time metrics</h2>
          <p>These are metrics generated using a message queue with user actions and storm topologies.</p>
          <select id="realtimemetrics">
			  <option value="ViewersPerChannel">Viewers Per Channel (Top 10)</option>
			  <option value="TotalViewers">Total Viewers</option>
			  <option value="ViewersPerType">Viewers Per Type</option>
			  <option value="ViewersPerFamilyGroup">Viewers Per Family Group</option>
			  <option value="ViewersPerCategory">Viewers Per Category</option>
		</select>
       </div>
        <div class="span4">
          <h2>Custom metrics</h2>
          <p>These are the metrics that we considered more relevant (when put together) for the study of the market.</p>
	      <div class="btn-group">
		      <button class="btn btn-success dropdown-toggle" data-toggle="dropdown">Action <span class="caret"></span></button>
		      <ul class="dropdown-menu">
		        <li><a href="#container" id="audiencepertype">Audience per client type</a></li>
		        <li><a href="#container" id="audienceperfg">Audience per family group</a></li>
		        <li><a href="#container" id="worstshows">Worst Shows</a></li>
		      </ul>
		    </div>
	      </div>
        </div>
      </div>
      
      <div id="container" style="width:100%; height:400px;"></div>

      <hr>

      <footer>
        <p>© Company 2013</p>
      </footer>

    </div>
<%@ include file="footer.jsp"%>