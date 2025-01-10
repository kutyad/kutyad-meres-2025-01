<?php
header('Content-Type: application/json');
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "nevnapok";  // Az adatbázis neve

// Kapcsolódás az adatbázishoz
$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if (isset($_GET['nap'])) {
    $nap = $_GET['nap'];
    $sql = "SELECT * FROM nevnapok WHERE datum = '$nap'";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        echo json_encode(array('datum' => $nap, 'nevnap1' => $row['nev1'], 'nevnap2' => $row['nev2']));
    } else {
        echo json_encode(array("hiba" => "nincs találat"));
    }
} elseif (isset($_GET['nev'])) {
    $nev = $_GET['nev'];
    $sql = "SELECT * FROM nevnapok WHERE nev1 LIKE '%$nev%' OR nev2 LIKE '%$nev%'";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        echo json_encode(array('datum' => $row['datum'], 'nevnap1' => $row['nev1'], 'nevnap2' => $row['nev2']));
    } else {
        echo json_encode(array("hiba" => "nincs találat"));
    }
} else {
    echo json_encode(array(
        "minta1" => "/?nap=12-31",
        "minta2" => "/?nev=Szilveszter"
    ));
}

$conn->close();
?>
