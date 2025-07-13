#!/bin/bash

echo "==============================================="
echo "            Todo App 테스트 실행"
echo "==============================================="

echo ""
echo "[1/4] 단위 테스트 실행 중..."
./gradlew test --tests "*Test" --info

echo ""
echo "[2/4] 통합 테스트 실행 중..."
./gradlew test --tests "*IntegrationTest" --info

echo ""
echo "[3/4] 보안 테스트 실행 중..."
./gradlew test --tests "*SecurityTest" --info

echo ""
echo "[4/4] 전체 테스트 실행 중..."
./gradlew test

echo ""
echo "==============================================="
echo "테스트 완료!"
echo "테스트 리포트: build/reports/tests/test/index.html"
echo "==============================================="
