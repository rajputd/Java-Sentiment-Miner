
document.addEventListener("DOMContentLoaded", function() {
    //get html element that will contain the chart
    var ctx = document.getElementById("sentimentDisplay").getContext('2d');

    if (dataset == false) {
        dataset = [33,33,33];
    }

    var chart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ["positive", "negative", "neutral"],
            datasets: [{
                label: '# of tweets',
                data: dataset, //provided by backend
                backgroundColor: [
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                ],
                borderColor: [
                    'rgba(54, 162, 235, 1)',
                    'rgba(255,99,132,1)',
                    'rgba(255, 206, 86, 1)',
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero:true
                    }
                }]
            }
        }
    });

    chart.responsive = true;

}, false);
