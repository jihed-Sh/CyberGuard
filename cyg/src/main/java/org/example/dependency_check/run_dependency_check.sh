#!/bin/bash

# Check if the required argument is provided
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <path_to_project>"
    exit 1
fi

project_path="$1"

# Run OWASP Dependency-Check
~/Downloads/dependency-check/bin/dependency-check.sh --prettyPrint --format HTML -scan "$project_path"
