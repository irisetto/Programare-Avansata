<!DOCTYPE html>
<html>
<head>
    <title>Report</title>
</head>
<body>
<h1>Catalog Report</h1>
<p>${catalog.getName()}</p>
<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Path</th>
    </tr>
    <#list catalog.getDocs() as doc>
        <tr>
            <td>${doc.getId()}</td>
            <td>${doc.getTitle()}</td>
            <td>${doc.getLocation()}</td>
        </tr>
    </#list>
</table>
</body>
</html>
