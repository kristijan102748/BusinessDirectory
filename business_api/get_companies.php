<?php
require 'config.php';

$category = isset($_GET['category']) ? $conn->real_escape_string($_GET['category']) : '';
$search   = isset($_GET['search']) ? $conn->real_escape_string($_GET['search']) : '';

$sql = "SELECT * FROM companies WHERE 1=1";
if (!empty($category)) {
    $sql .= " AND categories LIKE '%$category%'";
}
if (!empty($search)) {
    $sql .= " AND name LIKE '%$search%'";
}

$result = $conn->query($sql);
$companies = [];

while ($row = $result->fetch_assoc()) {
    $companies[] = $row;
}

echo json_encode($companies);
?>