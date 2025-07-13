# Todo API 테스트 스크립트
Write-Host "=== Todo API 테스트 시작 ===" -ForegroundColor Green

# 기본 URL 설정
$baseUrl = "http://localhost:9090/api/todos"

# 1. 전체 Todo 조회
Write-Host "`n1. 전체 Todo 조회:" -ForegroundColor Yellow
try {
    $todos = Invoke-RestMethod -Uri $baseUrl -Method GET
    $todos | ConvertTo-Json -Depth 3
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

# 2. 새 Todo 생성
Write-Host "`n2. 새 Todo 생성:" -ForegroundColor Yellow
$newTodo = @{
    title = "독서하기"
    description = "하루 30분 독서하기"
} | ConvertTo-Json

try {
    $createdTodo = Invoke-RestMethod -Uri $baseUrl -Method POST -Body $newTodo -ContentType "application/json"
    Write-Host "생성된 Todo:" -ForegroundColor Green
    $createdTodo | ConvertTo-Json -Depth 3
    $todoId = $createdTodo.id
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

# 3. 특정 Todo 조회
if ($todoId) {
    Write-Host "`n3. Todo ID $todoId 조회:" -ForegroundColor Yellow
    try {
        $todo = Invoke-RestMethod -Uri "$baseUrl/$todoId" -Method GET
        $todo | ConvertTo-Json -Depth 3
    } catch {
        Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
    }

    # 4. Todo 수정
    Write-Host "`n4. Todo ID $todoId 수정:" -ForegroundColor Yellow
    $updateTodo = @{
        title = "독서하기 (수정됨)"
        description = "하루 1시간 독서하기로 변경"
        isDone = $true
    } | ConvertTo-Json

    try {
        $updatedTodo = Invoke-RestMethod -Uri "$baseUrl/$todoId" -Method PUT -Body $updateTodo -ContentType "application/json"
        Write-Host "수정된 Todo:" -ForegroundColor Green
        $updatedTodo | ConvertTo-Json -Depth 3
    } catch {
        Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
    }
}

# 5. 수정 후 전체 Todo 조회
Write-Host "`n5. 수정 후 전체 Todo 조회:" -ForegroundColor Yellow
try {
    $allTodos = Invoke-RestMethod -Uri $baseUrl -Method GET
    $allTodos | ConvertTo-Json -Depth 3
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`n=== Todo API 테스트 완료 ===" -ForegroundColor Green
Write-Host "`n참고:" -ForegroundColor Cyan
Write-Host "- API 서버: $baseUrl"
Write-Host "- H2 Console: http://localhost:9090/h2-console"
Write-Host "- 애플리케이션이 실행 중인지 확인하세요!"
