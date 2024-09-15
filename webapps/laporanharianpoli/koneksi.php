<?php
    $servername = "192.168.2.156";
    $username = "sik";
    $password = "sik";
    $database = "sik";

    $koneksi = new mysqli($servername, $username, $password, $database);

    if ($koneksi -> connect_error) {
        die("Connection Failed: ". $koneksi->connect_error);
    }