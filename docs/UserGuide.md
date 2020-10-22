---
layout: page
title: User Guide
---
---

## Table Of Contents

[1. Introduction](#introduction) <br />
 [1.1. What our app manages](#what-our-app-manages) <br />
[2. Quick start](#quick-start) <br />
[3. Features](#features) <br />
 [3.1. Company](#company) <br />
  [3.1.1. Adding a company: `add com`](#adding-a-company-add-com) <br />
  [3.1.2. Deleting a company: `delete com`](#deleting-a-company-delete-com) <br />
  [3.1.3. Editing a company: `edit com`](#editing-a-company-edit-com) <br />
  [3.1.4. Viewing a company: `view com`](#viewing-a-company-view-com) <br />
 [3.2. Internship](#internship) <br />
  [3.2.1. Adding an internship: `add int`](#adding-an-internship-add-int) <br />
  [3.2.2. Deleting an internship: `delete int`](#deleting-an-internship-delete-int) <br />
  [3.2.3. Editing an internship: `edit int`](#editing-an-internship-edit-int) <br />
 [3.3. Application](#application) <br />
  [3.3.1. Adding an application: `add app`](#adding-an-application-add-app) <br />
  [3.3.2. Deleting an application: `delete app`](#deleting-an-application-delete-app) <br />
  [3.3.3. Editing an application: `edit app`](#editing-an-application-edit-app) <br />
  [3.3.4. Viewing an application: `view app`](#viewing-an-application-view-app) <br />
 [3.4. Profile](#profile) <br />
  [3.4.1. Adding item to profile: `add me`](#adding-item-to-profile-add-me) <br />
  [3.4.2. Deleting item in profile: `delete me`](#deleting-item-in-profile-delete-me) <br />
  [3.4.3. Editing item in profile: `edit me`](#editing-item-in-profile-edit-me) <br />
  [3.4.4. Viewing item in profile: `view me`](#viewing-item-in-profile-view-me) <br />
 [3.5 General](#general) <br /> 
  [3.5.1. Switching Tabs: `switch`](#switching-tabs-switch) <br />
  [3.5.2. Viewing Help: `help`](#viewing-help-help) <br />
  [3.5.3. Exiting the program: `exit`](#exiting-the-program-exit) <br />
[4. Command Summary](#command-summary) <br />

---

## Introduction

InternHunter is a CLI-centric desktop application which aids university students in applying for tech internships.
It tracks and leverages on key metrics and information to create an optimal internship hunting experience.

#### What our app manages

InternHunter allows the management of four data types:

Data type | Alias | Attributes | What it represents
----------|-------|------------|-------------------
Company | com | company name, phone number, email, address, industry types, internships | A company offering internships
Internship | int | company name, job title, period, wage, requirements | An internship offered by a company
Application | app | internship, status, status date | An internship application that you applied for
Profile Item | me | category, title, descriptors | An item in your profile

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `InternHunter.jar` from [here](https://github.com/AY2021S1-CS2103T-T15-4/tp/releases/tag/v1.2).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app or run `java -jar InternHunter.jar` from the terminal.

1. The [Features](#features) available in the current version of InternHunter are listed below.

--------------------------------------------------------------------------------------------------------------------

## Features

**:information_source: Notes about the command format:**<br>

* Commands that deal with Company, Internship, Application, and Profile can only be executed when you are on the appropriate tab in the app.
The tab you must be on to execute a certain command is stated under each relevant section header in this guide.

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add com n/COMPANY_NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/INDUSTRY]...`, `COMPANY_NAME`, `PHONE_NUMBER`, `EMAIL`, `ADDRESS`, `INDUSTRY`,
  are parameters which can be used as `add com n/Google p/65218000 e/GoogleHire@gmail.com a/70 Pasir Panjang Rd, #03-71 t/Cloud Computing t/Artificial Industry`.

* Items in square brackets are optional.<br>
  e.g `add int INDEX j/JOB_TITLE w/WAGE [p/PERIOD] [r/REQUIREMENT]...` can be used as <br/> `add int 1 j/Software Engineer` or
  `add int 3 j/Web Developer r/React w/3000 r/HTML5`.

* Items with `...`​ after them can be used multiple times including zero times.<br>
  e.g. `[r/REQUIREMENT]...` can be used as `r/Rust`, `r/React native r/JavaScript` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `p/PERIOD w/WAGE`, `w/WAGE p/PERIOD` is also acceptable.

* `INDEX` refers to the index of the item (Company, Application, or Profile Item) in its respective displayed list of items (follows one-based indexing) unless stated otherwise. <br>


### Company

Note: You must be on the **Company** tab in order to execute these commands.

#### Adding a company: `add com`

Adds a company to your list of companies.

Format: `add com n/COMPANY_NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/INDUSTRY]...`

Examples:
- `add com n/Google p/65218000 e/GoogleHires@gmail.com a/70 Pasir Panjang Rd, #03-71 t/Cloud Computing t/Artificial Intelligence`
- `add com n/Garena a/201 Victoria St e/GarenaHires@gmail.com p/65093545`

#### Deleting a company: `delete com`

Deletes a company from your list of companies. All internships and applications associated with this company will also be deleted.

Format: `delete com INDEX`

Example:
- `delete com 5`

#### Editing a company: `edit com`

Edits a company in your list of companies.

Format: `edit com INDEX [n/COMPANY_NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/INDUSTRY]...`
- At least one of the optional fields must be provided.

Examples:
- `edit com 1 n/Google Singapore e/GoogleIsHiring@gmail.com`
- `edit com 2 p/61234567`

#### Viewing a company: `view com`

Selects a company in your list of companies to show in detail.

Format: `view com INDEX`

Example:
- `view com 3`

### Internship

Note: You must be on the **Company** tab in order to execute these commands.

#### Adding an internship: `add int`

Adds an internship to a company.

Format: `add int INDEX j/JOB_TITLE w/WAGE [p/PERIOD] [r/REQUIREMENT]...`
- `PERIOD` can refer to any word (e.g. 3 months, Summer break)

Examples:
- `add int 1 j/Software Engineer w/1512`
- `add int 3 j/Web Developer w/3000 r/React r/HTML5`

#### Deleting an internship: `delete int`

Deletes an internship from a company. The application (if any) made with this internship will also be deleted.
 
Format: `delete int INDEX i/INDEX` 
- `INDEX` refers to the index of the company in the company list, and `i/INDEX` refers to the index of the
internship in the company’s list of internships.

Example:
- `delete int 3 i/2`

#### Editing an internship: `edit int`

Edits an internship from a company.

Format:  `edit int INDEX i/INDEX [j/JOB_TITLE] [w/WAGE] [p/PERIOD] [r/REQUIREMENT]...`
- `INDEX` refers to the index of the company in the company list, and `i/INDEX` refers to the index of the
internship in the company’s list of internships.
- At least one of the optional fields must be provided.

Examples:
- `edit int 7 i/1 w/2000 r/Java r/Python`
- `edit int 4 i/4 j/ML Engineer`

### Application

Note: You must be on the **Company** tab in order to execute the add app command. To execute all other commands of type Application, you
must be on the **Application** tab.

Each application comes with a `STATUS` and `STATUS_DATE` field that indicates the date that the status was set/updated.

Valid `STATUS` specifiers:
- `applied`
- `interview`
- `waiting`
- `rejected`
- `offered`
- `accepted`

Valid `STATUS_DATE` formats:

- d-M-yy HHmm
    - e.g. `23-12-19 2230`
- d-M-yy
    - e.g. `23-12-19`
    - Time will be taken as 2359

:information_source: Note that dates added must be in the future

#### Adding an application: `add app`

Selects an internship from a company and adds it to your list of applications. If unspecified, the application’s
`STATUS` will be `Applied`, and it’s `STATUS_DATE` will be set as today’s date.

Format: `add app INDEX i/INDEX [s/STATUS] [d/STATUS_DATE]`
- Where `INDEX` refers to the index of the company in the company list, and `i/INDEX` refers to the index of the
internship in the company’s list of internships.

Examples:
- `add app 1 i/2`
- `add app 1 i/2 s/waiting d/23-12-20`

#### Deleting an application: `delete app`

Deletes an application from your list of applications.
 
Format: `delete app INDEX` 

Example:
- `delete app 3`

#### Editing an application: `edit app`

Edits an application in your list of applications.

Format:  `edit app INDEX [s/STATUS] [d/STATUS_DATE]`
- At least one of the optional fields must be provided.

Examples:
- `edit app 5 s/offered`
- `edit app 2 s/waiting d/10-11-20`

#### Viewing an application: `view app`

Selects an application in the list of applications to show in detail.
 
Format: `view app INDEX`

Example:
- `view app 3`

### Profile

Note: You must be on the **Profile** tab in order to execute these commands.

Your profile can contain 3 categories of information.

Valid `CATEGORY` specifiers:
- `achievement`
- `experience`
- `skill`

Each profile item (bit of information) also contains a `TITLE` that describes the item, and you can optionally 
add additional `DESCRIPTOR`s to further describe the item in point form.

#### Adding item to profile: `add me`

Adds experience, skills or achievements descriptors to your profile.

Format: `add me c/CATEGORY t/TITLE [d/DESCRIPTOR]...`

* Category specifies a category which can be either experience, skills or achievement.

Examples:
* `add me c/experience t/Internship at Govtech d/Implemented automate testing 
using TravisCI d/Implemented dashboard to track code coverage`
* `add me c/achievement t/special recognition in Hack n Roll`
* `add me c/skill t/HTML d/Learn how to create divs`

#### Deleting item in profile: `delete me`

Deletes experience, skills or achievements descriptors from your profile.

Format: `delete me INDEX`

Example:
* `delete me 2`

#### Editing item in profile: `edit me`

Edit the experience, skills or achievements descriptors of your profile.

Format: `edit me INDEX [c/CATEGORY] [t/TITLE] [d/DESCRIPTORS]`

* At least one of the optional fields must be provided.

Examples:
* `edit me 2 c/skill t/CSS d/learnt how to use flexbox`
* `edit me 4 c/achievement`

#### Viewing item in profile: `view me`

Selects an item in the profile to show in detail.

Format: `view me INDEX`

Example:
* `view me 3`

### General

#### Switching Tabs: `switch`

Switches between tabs.

Format: `switch TYPE`

There are three `TYPE`s:
* `com`
* `app`
* `me`

`com` refers to Company tab, `app` refers to Application tab, `me` refers to Profile tab.

Example: 
* `switch me`

#### Viewing Help: `help`
Displays a link to the InternHunter user guide.

Format: `help`

:bulb: You can press Esc key to close the help window!

#### Exiting the Program: `exit`
Shows an exit confirmation dialog.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## Command summary

Type            | Action     | Format
----------------|------------|------------------
**Company**     | **Add**    | `add com n/COMPANY_NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/INDUSTRY]...`
&nbsp;          | **Delete** | `delete com INDEX`
&nbsp;          | **Edit**   | `edit com INDEX [n/COMPANY_NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/INDUSTRY]...`
&nbsp;          | **View**   | `view com INDEX`
**Internship**  | **Add**    | `add int INDEX j/JOB_TITLE w/WAGE [p/PERIOD] [r/REQUIREMENT]...`
&nbsp;          | **Delete** | `delete int INDEX i/INDEX`
&nbsp;          | **Edit**   | `edit int INDEX i/INDEX [j/JOB_TITLE] [p/PERIOD] [w/WAGE] [r/REQUIREMENT]...`
**Application** | **Add**    | `add app INDEX i/INDEX [s/STATUS] [d/STATUS_DATE]`
&nbsp;          | **Delete** | `delete app INDEX`
&nbsp;          | **Edit**   | `edit app INDEX [s/STATUS] [d/STATUS_DATE]`
&nbsp;          | **View**   | `view app INDEX`
**Profile**     | **Add**    | `add me c/CATEGORY t/TITLE [d/DESCRIPTOR]...`
&nbsp;          | **Delete** | `delete me INDEX`
&nbsp;          | **Edit**   | `edit me INDEX [c/CATEGORY] [t/TITLE] [d/DESCRIPTOR]...`
&nbsp;          | **View**   | `view me INDEX`
**General**     | **Switch** | `switch TYPE`
&nbsp;          | **Help**   | `help`
&nbsp;          | **Exit**   | `exit`
