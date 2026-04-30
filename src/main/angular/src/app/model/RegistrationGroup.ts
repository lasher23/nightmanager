export interface RegistrationGroup {
  id: number;
  name: string;
  requiresAge: boolean;
  tournament: { id: number; name: string; state: string };
}
