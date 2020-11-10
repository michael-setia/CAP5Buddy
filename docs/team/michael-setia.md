---
layout: page
title: Michael Setia Atmaja's Project Portfolio Page
---

## Project: CAP 5 Buddy

### Project Overview

CAP5Buddy is a desktop module tracker application used to centralize key module details.
The application is  optimized for use via a Command Line Interface (CLI).
It is written in Java, and has about 40 kLoC.

### Summary of Contributions

Given below are my contributions to the project.

* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=michael-setia)

* **New Feature:** Added TodoList feature as a task manager.
  * **What it does:** This feature allows the user to track all the things that they need to do. Each task in the TodoList
  contains useful information that can effectively help the user to manage their task. In addition, this feature also gives
  some statistics about all the tasks that they currently have.
  * **Justification:** This feature helps the user to be more productive, especially in managing their module-related tasks.
  It adds value to the product by complementing the module tracker feature. 
  * **Highlights:** 
    * This feature requires integration in all the product components (from front end to back end).
    * Added TodoList base classes to the main code (Pull Request 
    [#243](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/243) 
    [#229](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/229))
    * Updated and refined GUI for TodoList (Pull Request 
    [#433](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/433)
    [#404](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/404))
    * Added commands for TodoList (Pull Request 
    [#627](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/627)
    [#244](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/244)
    [#307](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/307)
    [#309](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/309)
    [#311](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/311)
    [#339](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/339)
    [#451](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/451))
    * Added sorting function and command for TodoList (Pull Request 
    [#320](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/320)
    [#416](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/416))
    * Updated storage component for TodoList (Pull Request 
    [#412](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/412))

* **Enhancement:** Added sorting and marking feature for contact list
  * **What it does:** This feature allows the user to sort the contact list based on the name of the persons. In addition
  a feature to mark a contact as important is also added.
  * **Justification:** This feature helps the user to organize their contacts since it will be much easier if the list
  is sorted. Being able to mark the contact as important will also help the user find their important/oftenly contacted persons.
  * **Highlights:**
    * Added sorting function and command for TodoList (Pull Request 
    [#320](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/320))
    * Added marking function and command for TodoList (Pull Request 
    [#402](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/402))

* **Enhancement:** Implemented mechanism to store zoomlinks (storage component only)
  * **Highlights:**
    * Implemented storage component for zoomlinks (Pull request 
    [#421](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/421)
    [#546](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/546))

* **Project management:**
  * Actively managed issues in team repository
  * Managed and authored [75 issues](https://github.com/AY2021S1-CS2103T-F12-3/tp/issues?q=+is%3Aissue+author%3Amichael-setia+) in total
  * Edited label color to be more intuitive

* **Documentation:**
  * User Guide:
    * Added documentation under [**Todo List Features**] (https://ay2021s1-cs2103t-f12-3.github.io/tp/UserGuide.html#todo-list-features) and updated [**Command Summary**](https://ay2021s1-cs2103t-f12-3.github.io/tp/UserGuide.html#command-summary-for-todo-list) for TodoList
    (Pull Request [#338](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/338)
    [#474](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/474))
    * Added documentation for ContactList [**sort**](https://ay2021s1-cs2103t-f12-3.github.io/tp/UserGuide.html#sorting-contacts-sortcontact) and [**mark**](https://ay2021s1-cs2103t-f12-3.github.io/tp/UserGuide.html#marking-contacts-as-important-importantcontact) feature 
    (Pull Request [#474](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/474))
  * Developer Guide:
    * Added implementation details for [TodoList](https://ay2021s1-cs2103t-f12-3.github.io/tp/DeveloperGuide.html#todo-list)
    (Pull Request [#331](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/331)
    [#678](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/678))
    * Added implementation details for [Sort Contacts Feature](https://ay2021s1-cs2103t-f12-3.github.io/tp/DeveloperGuide.html#sort-contacts-feature) 
    (Pull Request [#678](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/678))
    * Added diagram for `Model` section (Pull Request [#323](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/323))

* **Testing:**
  * Added tests for most of the functions in TodoList feature (Pull Request 
  [#632](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/632))
  * Added tests for sorting and marking function in ContactList feature (Pull Request 
  [#676](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/676))
  * Added tests for some functions in ModuleTracker feature (Pull Request 
  [#629](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/629))
  * Refactored and added test files for ModuleTracker feature (Pull Request 
  [#209](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/209))

* **Bug fixing:**
  * Fixed bugs from PE-D reports with extensive description:
    * Issue (with description of the bug analysis and fix) : 
    [#532](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/532)
    [#535](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/535)
    * Pull Request :
    [#538](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/538)
    [#539](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/539)

* **Community:**
  * Reviewed and approved [66 PRs](https://github.com/AY2021S1-CS2103T-F12-3/tp/pulls?q=is%3Apr+reviewed-by%3A%40me) in total
  * Added total of 56 comments in other PRs (can be seen from [CS2103T tp comments dashboard](https://nus-cs2103-ay2021s1.github.io/dashboards/contents/tp-comments.html))
  * Some approved PRs with non-trivial comments : 
  [#316](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/316)
  [#553](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/553)
  [#405](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/405)
  [#435](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/435)
  [#555](https://github.com/AY2021S1-CS2103T-F12-3/tp/pull/555))
  * Reported bugs for other team during PE-D (can be seen [here](https://github.com/michael-setia/ped/issues))
