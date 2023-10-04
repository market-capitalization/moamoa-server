
# Github Rule
## Commit Message
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

## Branch
### main branch
- 제품으로 출시하는 브랜치입니다.
- 배포 가능한 상태만을 관리합니다. → 배포 이력을 관리합니다.

### develop branch
- 기능을 개발하는 브랜치들(feature branch)을 병합하기 위해 사용됩니다. 이후, 배포 가능한 상태가 된다면 develop 브랜치를 main 브랜치에 병합합니다.

### feature branch
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

## Pull Request
> [`branch-name`] feature-description
```
[feature/7-googleLogin] 구글 로그인
```
