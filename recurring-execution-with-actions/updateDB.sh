#!/bin/bash 
cd recurring-execution-with-actions
git add isahn_history.json
set +e  # Grep succeeds with nonzero exit codes to show results.
git status | grep modified
if [ $? -eq 0 ]
then
    set -e
    git config user.email "action@github.com"
    git config user.name "GitHub Action"
    git commit -am "updated on - $(date)"
    git push origin master
else
    set -e
    echo "No changes since last run"
fi

