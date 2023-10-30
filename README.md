<div align=center> 

제 2회 신한금융그룹 빅데이터 해커톤 - 신한투자증권부문 🏆️우수상🏆️ 수상작
# 모임 투자 서비스 '모아모아'
모임 통장과 유사하지만 현금 대신 주식을 자본으로 활용하는 모임 ‘투자’ 서비스📈

</div>

<!-- 
<img src="https://github.com/market-capitalization/.github/blob/main/img/0001.jpg?raw=true"/>
-->

<br>

## 💸 Team. 시가총액

<table align = "center">
  <tr align = "center">
    <td><a href="https://github.com/cyndii20"><img src="https://avatars.githubusercontent.com/u/90389093?v=4" width=200></a></td>
    <td><a href="https://github.com/5jisoo"><img src="https://avatars.githubusercontent.com/u/96935231?v=4" width=200></a></td>
    <td><a href="https://github.com/chaeging"><img src="https://avatars.githubusercontent.com/u/122455485?v=4" width=200></a></td>
    <td><a href="https://github.com/chennielee"><img src="https://avatars.githubusercontent.com/u/126068623?v=4" width=200></a></td>
  </tr>
  <tr align = "center">
    <td><a href = "https://github.com/cyndii20">서정연</a> <br> PM, Data Analyst, <br>Frontend Developer</td>
    <td><a href = "https://github.com/5jisoo">오지수</a> <br> Backend Developer </td>
    <td><a href = "https://github.com/chaeging">유채현</a> <br> Data Analyst </td>
    <td><a href = "https://github.com/chennielee">이채은</a> <br> Frontend Developer</td>
  </tr>

</table>
<br>

## 💸 주요 기능

<div align=center> 
<img width="85%" src="https://github.com/market-capitalization/.github/blob/main/img/0017.jpg?raw=true"/>
</div>

### 투자 모임 개설
- 모임 목적, 목표 금액, 목표 기간 등 설정하여 모임 개설
- 모임 상세 페이지에서는 총 자산, 달성률, 수익률, 투자한 주식 목록 등의 정보 확인
- 메인 페이지에서는 내가 참여한 모임들의 목록을 한눈에 파악 가능

<table align = "center">
  <tr align = "center">
    <td><img src="https://github.com/market-capitalization/.github/blob/main/img/0018.jpg?raw=true"/></td>
    <td><img src="https://github.com/market-capitalization/.github/blob/main/img/0019.jpg?raw=true"/></td>
  </tr>
</table>

### 주식 종목 추천
- 가입자의 연령대와 성별 정보를 바탕으로 신한카드 데이터 분석 결과를 활용하여 관련된 주식 종목 추천
- 투자하길 희망하는 주식을 구별하기 위한 좋아요 기능
<table align = "center">
  <tr align = "center">
    <td><img src="https://github.com/market-capitalization/.github/blob/main/img/0020.jpg?raw=true"/></td>
    <td><img src="https://github.com/market-capitalization/.github/blob/main/img/0021.jpg?raw=true"/></td>
  </tr>
<!--  <tr>
    <td><img src="https://github.com/market-capitalization/.github/blob/main/img/0022.jpg?raw=true"/></td>
  </tr> -->
</table>

<br>

## 💸 기대 효과

<div align="center">
  <img width="85%" src="https://github.com/market-capitalization/.github/blob/main/img/0025.jpg?raw=true"/>
</div>

### 젊은 세대의 위축된 투자심리 완화
- 주식 투자에 대한 진입장벽을 낮추고 입문의 기회를 제공

### 앱 유입량 증가와 고객층 확보
- 대표 MTS '신한알파'를 기반 플랫폼으로 사용하여 앱 자체의 유입량 증가를 도모
 
### 장기고객으로의 전환 강화
- 서비스 핵심 기능을 '모임' 투자와 확실한 '목적성'이 있는 모임임에 중점을 두어 장기고객으로의 유치 가능성을 높임

<br>

## Github Rule
### Commit Message
> `commit-type`: description
```
Feat: getProfileTab
```
- `commit-type`
    - Build : 빌드 관련 수정
    - Chore : 패키지 매니저 수정, 그 외 기타 수정 ex) .gitignore
    - Docs : 문서(주석) 수정
    - Feat : 새로운 기능 추가, 기존의 기능을 요구 사항에 맞추어 수정
    - Fix : 기능에 대한 버그 수정
    - Move: 파일 이동
    - Refactor : 전면 수정. 기능의 변화가 아닌 코드 리팩터링 ex) 변수 이름 변경
    - Release : 버전 릴리즈
    - Rename : 파일 이름 수정
    - Style : 코드 스타일, 포맷팅에 대한 수정
    - Test : 테스트 코드 추가/수정

### Branch
#### main branch
- 제품으로 출시하는 브랜치입니다.
- 배포 가능한 상태만을 관리합니다. → 배포 이력을 관리합니다.

#### develop branch
- 기능을 개발하는 브랜치들(feature branch)을 병합하기 위해 사용됩니다. 이후, 배포 가능한 상태가 된다면 develop 브랜치를 main 브랜치에 병합합니다.

#### feature branch
- develop branch에서 새로운 기능에 대한 feature 브랜치를 분기하여 사용합니다.
- 각 기능 개발 완료 후, develop 브랜치로 PR을 날려 merge합니다.
    - merge한 뒤, 이후에 사용하지 않는 브랜치라면 삭제하는 것이 좋습니다.
> `branch-type`/description
```
feature/13-like-post
```
- `branch-type`
    - 주로 `feature`를 사용합니다.
    - 어떤 이름도 가능하지만 `main`, `develop`, `release`, `hotfix` 등의 이름은 사용 불가능합니다.

### Pull Request
> [`branch-name`] feature-description
```
[feature/7-googleLogin] 구글 로그인
```
