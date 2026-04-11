#!/usr/bin/env bash
set -eu

if ! command -v gh >/dev/null 2>&1; then
  echo "ERROR: GitHub CLI (gh) no esta instalado." >&2
  exit 1
fi

gh auth status >/dev/null

gh label create setup --color "0E8A16" --description "Preparacion tecnica del proyecto" --force
gh label create work-item --color "C5DEF5" --description "Trabajo planificado del backlog" --force
gh label create backend --color "1D76DB" --description "Backend Java y Spring Boot" --force
gh label create frontend --color "5319E7" --description "Spring MVC y Thymeleaf" --force
gh label create database --color "006B75" --description "RavenDB, datos y consultas" --force
gh label create documentation --color "0075CA" --description "Documentacion del proyecto" --force
gh label create demo --color "FBCA04" --description "Material y flujo de demostracion" --force
gh label create high --color "B60205" --description "Prioridad alta" --force
gh label create medium --color "D93F0B" --description "Prioridad media" --force
gh label create low --color "0E8A16" --description "Prioridad baja" --force

for issue_file in issues/[0-9][0-9]_*.md; do
  title="$(sed -n 's/^# //p' "$issue_file" | head -n 1)"
  labels="$(sed -n 's/^<!-- github_labels: \(.*\) -->$/\1/p' "$issue_file" | head -n 1)"

  echo "Creating issue: $title"
  gh issue create --title "$title" --body-file "$issue_file" --label "$labels"
done
