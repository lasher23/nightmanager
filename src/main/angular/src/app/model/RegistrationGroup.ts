export interface RegistrationGroupInfoRow {
  label: string;
  value: string;
}

export interface RegistrationGroup {
  id: number;
  name: string;
  requiresAge: boolean;
  /** JSON-encoded RegistrationGroupInfoRow[] */
  info?: string | null;
  tournament: { id: number; name: string; state: string };
}

export function parseGroupInfo(info?: string | null): RegistrationGroupInfoRow[] {
  if (!info) return [];
  try {
    const rows = JSON.parse(info);
    return Array.isArray(rows) ? rows : [];
  } catch {
    return [];
  }
}

/**
 * Parses pasted text into rows. A line is "Label<TAB>Value" or "Label: Value";
 * lines without a separator continue the previous row's value.
 */
export function parseInfoText(text: string): RegistrationGroupInfoRow[] {
  const rows: RegistrationGroupInfoRow[] = [];
  const tabMode = text.includes('\t');
  for (const raw of text.split(/\r?\n/)) {
    const line = raw.trimStart();
    if (!line.trim()) continue;
    const tab = line.indexOf('\t');
    const colon = tabMode ? -1 : line.indexOf(':');
    const idx = tab >= 0 ? tab : colon;
    if (idx > 0 && idx <= 40) {
      rows.push({label: line.slice(0, idx).trim(), value: line.slice(idx + 1).trim()});
    } else if (rows.length) {
      rows[rows.length - 1].value += '\n' + line.trim();
    } else {
      rows.push({label: line.trim(), value: ''});
    }
  }
  return rows;
}

export function infoRowsToText(rows: RegistrationGroupInfoRow[]): string {
  return rows.map(r => `${r.label}\t${r.value}`).join('\n');
}
