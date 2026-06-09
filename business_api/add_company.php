<?php
require 'config.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $name       = $_POST['name'] ?? '';
    $address    = $_POST['address'] ?? '';
    $latitude   = floatval($_POST['latitude'] ?? 0);
    $longitude  = floatval($_POST['longitude'] ?? 0);
    $email      = $_POST['email'] ?? '';
    $phone      = $_POST['phone'] ?? '';
    $website    = $_POST['website'] ?? '';
    $categories = $_POST['categories'] ?? '';

    $sql = "INSERT INTO companies (name, address, latitude, longitude, email, phone, website, categories) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    $stmt = $conn->prepare($sql);
    $stmt->bind_param("ssddssss", $name, $address, $latitude, $longitude, $email, $phone, $website, $categories);

    if ($stmt->execute()) {
        echo json_encode(["success" => true, "message" => "Company added successfully"]);
    } else {
        echo json_encode(["success" => false, "message" => "Error: " . $stmt->error]);
    }
} else {
    echo json_encode(["success" => false, "message" => "Invalid request"]);
}
?>