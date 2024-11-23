<?php

$init = parse_ini_file("satusehat.ini");
$client_id = $init["client_id"];
$client_secret = $init["client_secret"];
$auth_url = $init["auth_url"];
$api_url = $init["api_url"];
$environment = $init["environment"];

include('auth.php');
include('function.php');

// nama petugas/operator Fasilitas Pelayanan Kesehatan (Fasyankes) yang akan melakukan validasi
$agent_name = '';

// NIK petugas/operator Fasilitas Pelayanan Kesehatan (Fasyankes) yang akan melakukan validasi
$agent_nik = '';

// auth to satusehat
$auth_result = authenticateWithOAuth2($client_id, $client_secret, $auth_url);

// Example usage
$json = generateUrl($agent_name, $agent_nik, $auth_result, $api_url, $environment);

$validation_web = json_decode($json, TRUE);

?><html>

<head>
  <script type="text/javascript">
    const url = "<?php echo $validation_web["data"]["url"] ?>"

    function loadFormPopup() {
      let params = `scrollbars=no,resizable=no,status=no,location=no,toolbar=no,menubar=no,width=0,height=0,left=100,top=100`;
      window.open(url, "KYC", params)
    }

    function loadFormNewTab() {
      window.open(url, "_blank")
    }
  </script>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>KYC SATSEHAT MOBILE RSBM</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      background-color: #f0f4f8;
      font-family: Arial, sans-serif;
      flex-direction: column;
      text-align: center; /* Untuk teks tengah */
    }

    img {
      width: 200px; /* Ukuran logo responsif */
      height: auto;
      margin-bottom: 15px;
    }

    button {
      padding: 12px 20px; /* Ukuran tombol lebih kecil untuk perangkat kecil */
      font-size: 16px;
      font-weight: bold;
      color: #ffffff;
      background-color: #4CAF50;
      border: none;
      border-radius: 8px;
      cursor: pointer;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      transition: transform 0.3s ease, background-color 0.3s ease;
      max-width: 90%; /* Membatasi lebar tombol di perangkat kecil */
    }

    button:hover {
      background-color: #45a049;
      transform: scale(1.05);
    }

    button:active {
      transform: scale(1);
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    }
    </style>
</head>

<body>
  <img src="images/logo.gif" alt="LOGO RUMAH SAKIT">
  <button onclick="loadFormNewTab()">KLIK UNTUK VERIFIKASI SATUSEHAT MOBILE</button>
</body>

</html>