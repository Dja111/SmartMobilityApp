<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>SmartMobility</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script>
        $(function() {
            $( "#datepicker2" ).datepicker(
                {
                    minDate:'today',

                });

            $("#datepicker1").datepicker({
                minDate:'today',
                onSelect: function (dateValue, inst) {
                    $("#datepicker2").datepicker("option", "minDate", dateValue)
                }
            });
        });
    </script>
</head>

<body class="bg-light">

<div class="container text-center">
    <div class="py-5 text-center">
        <img class="d-block mx-auto mb-4" src="https://smartmobilityusa.iqpc.com/UploadedFiles/EventPage/29038.001/images/Logo-lrg.png" alt="" width="250" height="140">
        <h2>SMART MOBILITY</h2>
        <#--<p class="lead">Below is an example form built entirely with Bootstrap's form controls. Each required form group has a validation state that can be triggered by attempting to submit the form without completing it.</p>-->
    </div>