$(document).ready(function() {

  // // Get input values from the HTML elements
  // var column = document.getElementById('columnname').value;
  // var start = document.getElementById('start').value;
  // var end = document.getElementById('end').value;
  // var condition = $('#conditioncolumn').val().trim();
  // var value = $('#value').val().trim();
  // var stats = $('#stats').val().trim();

  // Subset button click event handler
  $('#subsetbtn').click(function() {
    // Construct the URL for the subset request
    var url = 'http://127.0.0.1:8080/subset/' + $('#columnname').val() + '/' + $('#start').val().trim() + '/' + $('#end').val().trim();
    console.log(url);

    // Send an AJAX GET request to retrieve the subset data
    $.ajax({
      url: url,
      type: 'GET',
      crossDomain: true,
      dataType: 'json',
      success: function(data) {
        var resultsDiv = $('#resultbody');
        resultsDiv.html(''); // Clear previous results

        // Display the column name in the results
        var columnname = document.createElement('p')
        columnname.textContent = $('#columnname').val()
        resultsDiv.append(columnname)

        // Display each result item in the results
        for (var i = 0; i < data.length; i++) {
          var resultItem = document.createElement('p');
          resultItem.textContent = i+1 + "-   " +  data[i];
          resultsDiv.append(resultItem);
        }
      },
      error: function(xhr, status, error) {
        alert('Request failed with status: ' + status);
      }
    });
  });

  // Stats button click event handler
  $('#statsbtn').click(function() {
    // Construct the URL for the stats request
    var url = 'http://127.0.0.1:8080/aggregate/' + $('#aggregate_function').val() + '/'+ $('#conditioncolumn').val() + '/' + $('#value').val().trim() + '/' + $('#stats').val().trim();
    console.log(url);

    // Send an AJAX GET request to retrieve the stats data
    $.ajax({
      url: url,
      type: 'GET',
      crossDomain: true,
      dataType: 'json',
      success: function (data) {
        var resultsDiv = $('#resultbody');
        resultsDiv.html(''); // Clear previous results

        // Display the result item in the results
        var resultItem = document.createElement('p');
        resultItem.textContent = data;
        resultsDiv.append(resultItem);
      },
      error: function(xhr, status, error) {
        alert('Request failed with status: ' + status);
      }
    });
  });

});
