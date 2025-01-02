#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

typedef struct user {
    char *phoneNumber;
    char *userName;
} USER;

USER *expendDatabaseSize (USER *database, int *max_number_of_records) {                          //doubles the size of database and returns expanded database

    USER *tmpDatabase = NULL;
    tmpDatabase = (USER *) realloc(database, 2 * (*max_number_of_records) * sizeof(USER));

    if (tmpDatabase != NULL) {                                                                  // if reallocation is successful copies pointer tmpDatabase to database
        database = tmpDatabase;
    }
    return database;
}

char *allocUserDataArray (char *arrayToAlloc, int max_size_of_array) {                          // reallocates (doubles) memory for char array of either phone number or username

    char *tmpArray = NULL;
    tmpArray = (char *) realloc(arrayToAlloc, 2 * max_size_of_array * sizeof(char));

    if (tmpArray != NULL) {                                                                     // if reallocation is successful copies pointer tmpArray to arrayToAlloc
        arrayToAlloc = tmpArray;
    }
    return arrayToAlloc;
}

int searchNumberInDatabase (USER *database, int number_of_records, char *searchedNumber) {      // searches for searched phone number in database
    for (int i = 0; i < number_of_records;) {
        if (strcmp(database[i].phoneNumber, searchedNumber) == 0) {
            return i;                                                                           // if found returns index of user
        }
        i++;
    }
    return -1;                                                                                  // if not found returns -1
}

int compareTwoChars (char char1, char char2) {                                                  // compares two chars in T9 char1 is numeric char2 is alphanumeric (if matches returns 1)
    if (char1 == '1') {
        if (char2 == ' ' || char2 == '1')
            return 1;
    }
    if (char1 == '2') {
        if (char2 == '2' || char2 == 'a' || char2 == 'b' || char2 == 'c')
            return 1;
    }
    if (char1 == '3') {
        if (char2 == '3' || char2 == 'd' || char2 == 'e' || char2 == 'f')
            return 1;
    }if (char1 == '4') {
        if (char2 == '4' || char2 == 'g' || char2 == 'h' || char2 == 'i')
            return 1;
    }if (char1 == '5') {
        if (char2 == '5' || char2 == 'j' || char2 == 'k' || char2 == 'l')
            return 1;
    }if (char1 == '6') {
        if (char2 == '6' || char2 == 'm' || char2 == 'n' || char2 == 'o')
            return 1;
    }if (char1 == '7') {
        if (char2 == '7' || char2 == 'p' || char2 == 'q' || char2 == 'r' || char2 == 's')
            return 1;
    }if (char1 == '8') {
        if (char2 == '8' || char2 == 't' || char2 == 'u' || char2 == 'v')
            return 1;
    }if (char1 == '9') {
        if (char2 == '9' || char2 == 'w' || char2 == 'x' || char2 == 'y' || char2 == 'z')
            return 1;
    }if (char1 == '0') {
        if (char2 == ' ' || char2 == '1' || char2 == '2' || char2 == 'a' || char2 == 'b' || char2 == 'c' || char2 == '3' || char2 == 'd' || char2 == 'e' || char2 == 'f' || char2 == '4' || char2 == 'g' || char2 == 'h' || char2 == 'i' || char2 == '5' || char2 == 'j' || char2 == 'k' || char2 == 'l' || char2 == '6' || char2 == 'm' || char2 == 'n' || char2 == 'o' || char2 == '7' || char2 == 'p' || char2 == 'q' || char2 == 'r' || char2 == 's' || char2 == '8' || char2 == 't' || char2 == 'u' || char2 == 'v' || char2 == '9' || char2 == 'w' || char2 == 'x' || char2 == 'y' || char2 == 'z')
            return 0;
        else
            return 1;
    } else
        return 0;
}

int compareTwoCharArrays (char *array1, char *array2, long unsigned int length_of_array) {      //compares two arrays of chars in T9 if match return 0

    char *arrayCompare = (char *) malloc(length_of_array * sizeof(char));

    for (long unsigned int i = 0; i < length_of_array;) {
        arrayCompare[i] = tolower(array2[i]);                                                   // turns capital letters to lowercase (for use in compareTwoChars)
        i++;
    }

    for (long unsigned int i = 0; i < length_of_array;) {
        if (compareTwoChars(array1[i], arrayCompare[i]) == 0) {                                 // if any letter in T9 mismatches returns 1
            free(arrayCompare);
            return 1;
        }
        i++;
    }
    free(arrayCompare);
    return 0;

}


USER *addUser (USER *database, char *line_of_command, int *number_of_records) {                  // adds user data (username and phone number) to database or updates username if phone numbers matches

    unsigned long int length_of_line = strlen(line_of_command) - 1;
    char *tmpPhoneNumber = NULL;
    char *tmpUserName = NULL;
    int pn_char_number = 0;          //number of chars in phone number
    int un_char_number = 0;          //number of chars in username

    if (line_of_command[1] != ' ') {                                                          // checks if first character after command sign if space
        printf("INVALID COMMAND\n");
        return NULL;
    }
    unsigned long int i = 1;         // index of char in array that is currently function on

    for (;i < length_of_line;) {
        if (line_of_command[i] == ' ')                                                        // skips spaces before phone number
            i++;
        else
            break;
    }

    int max_size_of_array = 50;
    tmpPhoneNumber = (char *) malloc(max_size_of_array * sizeof(char));                  // allocation of char array for phone number

    for (;i < length_of_line;) {
        if (isdigit(line_of_command[i])) {                                                 // checks if char in phone number is a digit
            tmpPhoneNumber[pn_char_number] = line_of_command[i];                              // copies char of phone number
            i++;
            pn_char_number++;
            if (max_size_of_array <= (pn_char_number + 2)) {                                  //if chars in array are approaching limit (max_size_of_array) reallocates memory for phone number
                tmpPhoneNumber = allocUserDataArray(tmpPhoneNumber, max_size_of_array);
                max_size_of_array = max_size_of_array * 2;
            }
        } else if (line_of_command[i] == ' ') {                                               //breaks the cycle after it finds space after the phone number
            break;
        } else {
            free(tmpPhoneNumber);
            printf("INVALID COMMAND\n");                                               // if char in phone number is not a digit returns 1
            return NULL;
        }
    }
    for (;i < length_of_line;) {
        if (line_of_command[i] ==' ')                                                         // skips spaces between phone number and username
            i++;
        else
            break;
    }

    max_size_of_array = 50;                                                                   //resizes max size of array for username
    tmpUserName = (char *) malloc(max_size_of_array * sizeof(char));

    if (line_of_command[i] != '"') {
        printf("INVALID COMMAND\n");                                                  // checks quotation mark as a sign for beginning of the username
        free(tmpPhoneNumber);
        free(tmpUserName);
        return NULL;
    } else {
        i++;
        for (;i < length_of_line;) {
            if (line_of_command[i] != '"') {                                                // checks for the second quotation mark as a sign for the end of the username
                if (line_of_command[i] == '\\'){                                            // checks for backslash sign
                    i++;
                    tmpUserName[un_char_number] = line_of_command[i];                       //stores char after backslash into username
                }
                tmpUserName[un_char_number] = line_of_command[i];                           // copies chars to username until it reaches quotation mark
                i++;
                un_char_number++;

                if (max_size_of_array <= (un_char_number + 2)) {
                    tmpUserName = allocUserDataArray(tmpUserName, max_size_of_array);       // if array size is approaching limit (max_size_of_array) reallocates array
                    max_size_of_array = max_size_of_array * 2;
                }
            } else
                break;
        }
    }
    if (un_char_number == 0) {
        printf("INVALID COMMAND\n");                                       // if nothing is found between quotation marks prints invalid command and returns NULL
        free(tmpUserName);
        free(tmpPhoneNumber);
        return NULL;
    }
    i++;
    if (line_of_command[i] != EOF && line_of_command[i] != '\n') {
        printf("INVALID COMMAND\n");                                       // checks char after username (returns NULL when the next char after username is not EOF or new-line char
        free(tmpUserName);
        free(tmpPhoneNumber);
        return NULL;
    }

    tmpUserName = (char *) realloc(tmpUserName, un_char_number * sizeof(char)+1);                       // reallocates (shortens) array tmpData to their length (following extra data is freed)
    tmpPhoneNumber = (char *) realloc(tmpPhoneNumber, pn_char_number * sizeof(char)+1);
    tmpPhoneNumber[pn_char_number]=(char) 0;
    tmpUserName[un_char_number]=(char) 0;

    if (searchNumberInDatabase(database, *number_of_records, tmpPhoneNumber) != -1) {                        // if phone number already exists in the database updates phone number and writes UPDATED
        free(database[searchNumberInDatabase(database, *number_of_records, tmpPhoneNumber)].userName);
        database[searchNumberInDatabase(database, *number_of_records, tmpPhoneNumber)].userName = tmpUserName;
        free(tmpPhoneNumber);

        printf("UPDATED\n");
    } else {                                                                                            // if phone number does not exist in database creates new record


        database[*number_of_records].phoneNumber = tmpPhoneNumber;
        database[*number_of_records].userName = tmpUserName;

        *number_of_records = *number_of_records + 1;

        printf("NEW\n");


    }

    return database;
}

USER *removeUser (USER *database, char *line_of_command, int *number_of_records) {            // if phone numbers matches removes record of user

    unsigned long int length_of_line = strlen(line_of_command) - 1;
    char *tmpPhoneNumber = NULL;
    int pn_char_number = 0;   //number of chars in phone number

    if (line_of_command[1] != ' ') {                                                          // checks if first character after command sign if space
        printf("INVALID COMMAND\n");
        free(tmpPhoneNumber);
        return NULL;
    }
    unsigned long int i = 1;          // index of char in array that is currently function on

    for (;i < length_of_line;) {
        if (line_of_command[i] == ' ')                                                        // skips spaces before phone number
            i++;
        else
            break;
    }

    int max_size_of_array = 50;
    tmpPhoneNumber = (char *) malloc(max_size_of_array * sizeof(char));                  // allocation of char array for phone number

    for (;i < length_of_line;) {
        if (isdigit(line_of_command[i])) {                                                 // checks if char in phone number is a digit
            tmpPhoneNumber[pn_char_number] = line_of_command[i];                              // copies char of phone number
            i++;
            pn_char_number++;
            if (max_size_of_array <= (pn_char_number + 2)) {                                  //if chars in array are approaching limit (max_size_of_array) reallocates memory for phone number
                tmpPhoneNumber = allocUserDataArray(tmpPhoneNumber, max_size_of_array);
                max_size_of_array = max_size_of_array * 2;
            }
        } else if (line_of_command[i] == EOF || line_of_command[i] == '\n') {                 //ends reading of phone number when reaches EOF or new-line sign
            break;
        } else {
            free(tmpPhoneNumber);
            printf("INVALID COMMAND\n");                                               // if char in phone number is not a digit returns 1
            return NULL;
        }
    }

    tmpPhoneNumber[pn_char_number] = (char) 0;

    int x = searchNumberInDatabase(database, *number_of_records, tmpPhoneNumber);             //index of record which matches searched phone number

    if ( x != -1) {                                                                           // if number is found frees user data (user name and phone number) and moves last record to the newly created empty space
        free(database[x].phoneNumber);                                                        // frees data which were wanted to be deleted
        free(database[x].userName);
        if(*number_of_records > 1) {

            database[x].userName = database[(*number_of_records - 1)].userName;
            database[x].phoneNumber = database[(*number_of_records - 1)].phoneNumber;






        }
        *number_of_records = *number_of_records - 1;
        free(tmpPhoneNumber);

        printf("DELETED\n");

    } else {
        printf("NOT FOUND\n");
        free(tmpPhoneNumber);
    }
    return database;

}


USER *searchUser (USER *database, char *line_of_command, int *number_of_records) {            // searches for record in database based on phone number or T9

    unsigned long int length_of_line = strlen(line_of_command) - 1;
    char *tmpPhoneNumber = NULL;
    int pn_char_number = 0;     //number of chars in phone number

    if (line_of_command[1] != ' ') {                                                          // checks if first character after command sign if space
        printf("INVALID COMMAND\n");
        free(tmpPhoneNumber);
        return NULL;
    }
    unsigned long int i = 1;       // index of char in array that is currently function on

    for (;i < length_of_line;) {
        if (line_of_command[i] == ' ')                                                        // skips spaces before phone number
            i++;
        else
            break;
    }

    int max_size_of_array = 50;
    tmpPhoneNumber = (char *) malloc(max_size_of_array * sizeof(char));                  // allocation of char array for phone number

    for (;i < length_of_line;) {
        if (isdigit(line_of_command[i])) {                                                 // checks if char in phone number is a digit
            tmpPhoneNumber[pn_char_number] = line_of_command[i];                              // copies char of phone number
            i++;
            pn_char_number++;
            if (max_size_of_array <= (pn_char_number + 2)) {                                  //if chars in array are approaching limit (max_size_of_array) reallocates memory for phone number
                tmpPhoneNumber = allocUserDataArray(tmpPhoneNumber, max_size_of_array);
                max_size_of_array = max_size_of_array * 2;
            }
        } else if (line_of_command[i] == EOF || line_of_command[i] == '\n') {                 //ends reading of phone number when reaches EOF or new-line sign
            break;
        } else {
            free(tmpPhoneNumber);
            printf("INVALID COMMAND\n");                                               // if char in phone number is not a digit returns 1
            return NULL;
        }
    }

    tmpPhoneNumber[pn_char_number] = (char) 0;

    int number_of_pn_matches = 0;       //number of phone number matches (it can be either 0 or 1)
    int number_of_un_matches = 0;       //number of T9 matches with username

    if (searchNumberInDatabase(database, *number_of_records, tmpPhoneNumber) != -1) {         // searches for phone number matches in database
        number_of_pn_matches = 1;
    }
    int index_of_matched_array = 0;
    for (int j = 0; j < *number_of_records;) {                                                                      //searches for T9 match with username in database
        if (compareTwoCharArrays(tmpPhoneNumber, database[j].userName, strlen(database[j].userName)) == 0) {
            if (j == searchNumberInDatabase(database, *number_of_records, tmpPhoneNumber)) {                        // if finds same record as before
                j++;
            } else {
                number_of_un_matches++;
                index_of_matched_array = j;
                j++;
            }
        } else
            j++;
    }

    int total_matches = number_of_pn_matches + number_of_un_matches;                                                //calculates total matches and prints either AMBIGUOUS, FOUND or NOT FOUND
    if (total_matches > 1) {
        printf("AMBIGUOUS (%d matches)\n", total_matches);
    } else if (number_of_pn_matches == 1 && number_of_un_matches == 0) {
        printf("FOUND %s (%s)\n", tmpPhoneNumber, database[searchNumberInDatabase(database, *number_of_records, tmpPhoneNumber)].userName);
    } else if (number_of_pn_matches == 0 && number_of_un_matches == 1) {
        printf("FOUND %s (%s)\n", database[index_of_matched_array].phoneNumber, database[index_of_matched_array].userName);
    } else if (total_matches == 0) {
        printf("NOT FOUND\n");
    }
    free(tmpPhoneNumber);
    return database;
}


USER *readLineOfCommand (USER *database, int *number_of_records, int *max_number_of_records) {                // reads line of command and based of the command sign (1st char) calls command function

    char *line_of_command = NULL;          //line od command from stdin
    size_t size;
    USER *tmpDatabase = NULL;              //temporary database for check if command funcion is successful

    *number_of_records = 0;

    while (getline(&line_of_command, &size, stdin) != -1) {


        if (*number_of_records + 2 > *max_number_of_records) {                      //when number of records in database is approaching max limit reallocates (extends) database
            database = expendDatabaseSize(database, max_number_of_records);
            *max_number_of_records = *max_number_of_records * 2;
        }

        char command_sign = line_of_command[0];

        if (command_sign == '+') {
            tmpDatabase = addUser(database, line_of_command, number_of_records);
            if (tmpDatabase != NULL) {
                database = tmpDatabase;
            }
        } else if (command_sign == '-') {
            tmpDatabase = removeUser(database, line_of_command, number_of_records);
            if (tmpDatabase != NULL) {
                database = tmpDatabase;
            }
        } else if (command_sign == '?') {
            tmpDatabase = searchUser(database, line_of_command, number_of_records);
            if (tmpDatabase != NULL) {
                database = tmpDatabase;
            }
        } else {
            printf("INVALID COMMAND\n");
        }
        free(line_of_command);
        line_of_command = NULL;
    }
    free(line_of_command);
    line_of_command = NULL;
    return database;
}

void freeMemory (USER *database, int number_of_records) {          //frees database
    for (int i = 0; i < number_of_records;) {
        free(database[i].userName);
        free(database[i].phoneNumber);
        i++;
    }
    free(database);
}


int main() {

    printf("PBX configuration (+ = set, - = delete, ? = test, EOF = quit):\n");

    int number_of_records = 0;
    int max_number_of_records = 50;
    USER *database = NULL;
    database = (USER *) malloc(50 * sizeof(USER));
    database = readLineOfCommand(database, &number_of_records, &max_number_of_records);

    freeMemory(database, number_of_records);
    return 0;
}
