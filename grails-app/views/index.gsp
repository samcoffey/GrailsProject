<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	  	<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
		<script src="${resource(dir: 'js', file: 'bootstrap.min.js')}"></script>

		<link href="${resource(dir: 'css', file: 'bootstrap.min.css')}" rel="stylesheet">
 		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
   		 <title>VLEAP</title>
		<style type="text/css" media="screen">
		    .navbar-nav li a {font-size:30px;}
			#map 
			{
    			width:290px;height:340px;border:1px solid grey;padding: 0 0 0 0;margin:10px !important;
			}
		
			p 
			{
		   s 	margin:10px; font:10pt sans-serif;
			}
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main">
			
			<div id="controller-list" role="navigation">
				<h2>Available Controllers:</h2>
				<ul>
					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
					</g:each>
				</ul>
			</div>
		</div>
              
			<div style="text-align:center"  role="navigation" class="navbar navbar-fixed-top navbar-inverse ">
				    <div class="navbar-header">
					 <a class="navbar-brand" href="#"><img src="images/logo.jpg" class="img-responsive "/></a>
					  <button type="button" class="navbar-toggle" data-toggle="collapse" 
						 data-target="#navbar-collapse">
						 <span class="sr-only">Toggle navigation</span>
						 <span class="icon-bar"></span>
						 <span class="icon-bar"></span>
						 <span class="icon-bar"></span>
					  </button>  
					  
				    </div>
				<div class="collapse navbar-collapse" id="navbar-collapse navbar-inverse">
				
					<ul class="nav navbar-nav ">
						<li><a href="vhome.html">Home</a></li>
						<li><a href="vtopup.html">Top-Up</a></li>
						<li><a href="vactivity.html" >My Activity</a></li>
						<li><a href="vcard.html" >My Card</a></li>
					</ul>
				</div>
            </div>
		<img src="${resource(dir: 'images', file: 'profile.jpg')}" class="img-responsive "/><hr>
		<img src="${resource(dir: 'images', file: 'profile2.jpg')}" class="img-responsive "/><hr>
		<g:link mapping = "simpview" ><img src="${resource(dir: 'images', file: 'button.jpg')}" class="img-responsive "/></g:link><hr>
		<img src="${resource(dir: 'images', file: 'locat.jpg')}" class="img-responsive "/>
		
		<script src="http://openlayers.org/api/OpenLayers.js"></script>
		<div id="map" class="smallmap"></div>
		<p id="info"></p>
		
		<script>
// Center on Geneva first
var lat=46.2078;
var lon=6.147;
var zoom=14;

var map = new OpenLayers.Map("map");
        var mapnik = new OpenLayers.Layer.OSM();
        map.addLayer(mapnik);

var markers = new OpenLayers.Layer.Markers( "Markers" );
map.addLayer(markers);

// Recenter on user position
navigator.geolocation.getCurrentPosition(function(position) {       
        document.getElementById('info').innerHTML
            = " Latitude: " + 
                position.coords.latitude + 
              " Longitude: " +
                position.coords.longitude;

        var lonLat = new OpenLayers.LonLat(position.coords.longitude,
                                position.coords.latitude)
                  .transform(
                              new OpenLayers.Projection("EPSG:4326"), //transform from WGS 1984
                              map.getProjectionObject() //to Spherical Mercator Projection
                    );
                                        
        markers.addMarker(new OpenLayers.Marker(lonLat));
       
        map.setCenter(lonLat, 14 // Zoom level
        );

    });


</script> 
		
	</body>
</html>
