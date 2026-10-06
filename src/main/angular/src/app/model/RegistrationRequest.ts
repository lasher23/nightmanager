import {RegistrationGroup} from './RegistrationGroup';

export type RegistrationRequestStatus = 'PENDING' | 'APPROVED' | 'REJECTED';

export const STATUS_LABELS: Record<RegistrationRequestStatus, string> = {
  PENDING: 'Ausstehend',
  APPROVED: 'Genehmigt',
  REJECTED: 'Abgelehnt',
};

export interface RegistrationRequest {
  id: number;
  teamName: string;
  teamLeader?: string | null;
  contactEmail: string;
  status: RegistrationRequestStatus;
  registrationGroup: RegistrationGroup;
  memberBirthdays: string[];
  createdAt: string;
}
