$output = "Partha-all-java-files.txt"
Remove-Item $output -ErrorAction Ignore

Get-ChildItem -Path . -Recurse -File -Filter *.java |
Where-Object {
    $_.Name -notmatch '^\.' -and
    $_.FullName -notmatch '\\target\\' -and
    $_.FullName -notmatch '\\test\\'
} |
Sort-Object FullName |
ForEach-Object {
    "=================== $($_.FullName) ==================="
    Get-Content $_.FullName
    ""
} | Set-Content $output
